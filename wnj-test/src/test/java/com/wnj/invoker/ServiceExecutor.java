package com.wnj.invoker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ServiceExecutor {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            100
            , 100
            , 5
            , TimeUnit.MINUTES
            , new LinkedBlockingDeque<>(100)
            , new NamedThreadFactory("service-invoke"));


    public <T> List<T> executeSupplier(List<Supplier<T>> suppliers){
        List<RpcCallBack<T>> rpcCallBacks = new ArrayList<>(suppliers.size());
        for (Supplier<T> supplier : suppliers) {
            rpcCallBacks.add(AbstractRpcCallBack.build(supplier));
        }
        return execute(rpcCallBacks);
    }

    public final <T> Future<T> execute(ServiceInvoker<T> serviceInvoker){
        ServiceInvokeResult result = new ServiceInvokeResult();
        Future<ServiceInvokeResult<T>> future = null;
        try {
            future = threadPoolExecutor.submit(serviceInvoker);
        } catch (Exception e) {
            result.setThrowable(e);
        }
        return new ServiceFuture(future, 5000);
    }

    public final <T> List<T> execute(List<RpcCallBack<T>> callBacks){
        List<Future<T>> futures = new ArrayList<>(callBacks.size());
        for (RpcCallBack<T> callBack : callBacks) {
            futures.add(execute(new ServiceInvoker<>(callBack)));
        }

        List<T> results = new ArrayList<>(futures.size());

        for (Future<T> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            indexs.add(i);
        }
        List<Supplier<ServiceInvokeResult>> suppliers = indexs.stream().map(index -> new Supplier<ServiceInvokeResult>() {
            @Override
            public ServiceInvokeResult get() {
                System.out.println("start..." + index);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(LocalDateTime.now() + "..." + index);
                return new ServiceInvokeResult("result-" + index);
            }
        }).collect(Collectors.toList());
        ServiceExecutor serviceExecutor = new ServiceExecutor();


        List<ServiceInvokeResult> serviceInvokeResults = serviceExecutor.executeSupplier(suppliers);
        for (ServiceInvokeResult serviceInvokeResult : serviceInvokeResults) {
            System.out.println(serviceInvokeResult.getData());
        }
        System.out.println("......end");

        System.exit(0);
    }
}
