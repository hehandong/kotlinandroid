package com.example.kotlinandroid.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//fun main() {
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("World!") // 在延迟后打印输出
//    }
//    println("Hello,") // 协程已在等待时主线程还在继续
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
//}


//fun main() = runBlocking<Unit> { // 开始执行主协程
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,") // 主协程在这里会立即执行
//    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
//}


//fun main() = runBlocking {
//    val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    job.join() // 等待直到子协程执行结束
//}

//fun main() = runBlocking { // this: CoroutineScope
//    launch { // 在 runBlocking 作用域中启动一个新协程
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//}



/*
TODO:作用域构建器
除了由不同的构建器提供协程作用域之外，还可以使用 coroutineScope 构建器声明自己的作用域。它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束。

runBlocking 与 coroutineScope 可能看起来很类似，因为它们都会等待其协程体以及所有子协程结束。
主要区别在于，runBlocking 方法会阻塞当前线程来等待， 而 coroutineScope 只是挂起，会释放底层线程用于其他用途。
由于存在这点差异，runBlocking 是常规函数，而 coroutineScope 是挂起函数。*/

/*
fun main() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope { // 创建一个协程作用域
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}*/


//fun main() = runBlocking {
//    launch { doWorld() }
//    println("Hello,--Thread${Thread.currentThread()}")
//}
//
//// 这是你的第一个挂起函数
//suspend fun doWorld() {
//    delay(1000L)
//    println("World!--Thread${Thread.currentThread()}")
//}


/*TODO:取消协程的执行
在一个长时间运行的应用程序中，你也许需要对你的后台协程进行细粒度的控制。
比如说，一个用户也许关闭了一个启动了协程的界面，那么现在协程的执行结果已经不再被需要了，这时，它应该是可以被取消的。
该 launch 函数返回了一个可以被用来取消运行中的协程的 Job：*/
//fun main() = runBlocking {
//    val job = launch {
//        repeat(1000) { i ->
//            println("job: I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//    delay(1300L) // 延迟一段时间
//    println("main: I'm tired of waiting!")
//    job.cancel() // 取消该作业
//    job.join() // 等待作业执行结束
//    println("main: Now I can quit.")
//}



suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // 假设我们在这里做了些有用的事
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假设我们在这里也做了一些有用的事
    return 29
}

//默认顺序调用
//fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        val one = doSomethingUsefulOne()
//        val two = doSomethingUsefulTwo()
//        println("The answer is ${one + two}")
//    }
//    println("Completed in $time ms")
//}


/*
使用 async 并发
如果 doSomethingUsefulOne 与 doSomethingUsefulTwo 之间没有依赖，并且我们想更快的得到结果，让它们进行 并发 吗？这就是 async 可以帮助我们的地方。

在概念上，async 就类似于 launch。它启动了一个单独的协程，这是一个轻量级的线程并与其它所有的协程一起并发的工作。
不同之处在于 launch 返回一个 Job 并且不附带任何结果值，而 async 返回一个 Deferred —— 一个轻量级的非阻塞 future， 这代表了一个将会在稍后提供结果的 promise。
你可以使用 .await() 在一个延期的值上得到它的最终结果， 但是 Deferred 也是一个 Job，所以如果需要的话，你可以取消它。
*/

//fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        val one = async { doSomethingUsefulOne() }
//        val two = async { doSomethingUsefulTwo() }
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}



//fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
//        // 执行一些计算
//        one.start() // 启动第一个
//        two.start() // 启动第二个
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}


//fun main() = runBlocking<Unit> {
//    launch { // 运行在父协程的上下文中，即 runBlocking 主协程
//        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
//        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Default) { // 将会获取默认调度器
//        println("Default               : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.IO) { // 将会获取默认调度器
//        println("IO                  : I'm working in thread ${Thread.currentThread().name}")
//    }
//
//    launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
//        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//    }
//}





