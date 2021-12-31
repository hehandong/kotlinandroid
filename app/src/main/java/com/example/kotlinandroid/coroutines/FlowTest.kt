package com.example.kotlinandroid.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun simple(): List<Int> = listOf(1, 2, 3)

//fun main() {
//    simple().forEach { value -> println(value) }
//}




/*TODO：挂起函数
然而，计算过程阻塞运行该代码的主线程。
当这些值由异步代码计算时，我们可以使用 suspend 修饰符标记函数 simple，
这样它就可以在不阻塞的情况下执行其工作并将结果作为列表返回：*/

//suspend fun simple(): List<Int> {
//    delay(1000) // 假装我们在这里做了一些异步的事情
//    return listOf(1, 2, 3)
//}
//
//fun main() = runBlocking<Unit> {
//    simple().forEach { value -> println(value) }
//}


//TODO:流
//使用 List 结果类型，意味着我们只能一次返回所有值。
//为了表示异步计算的值流（stream），我们可以使用 Flow 类型（正如同步计算值会使用 Sequence 类型）：

//fun simple(): Flow<Int> = flow { // 流构建器
//    for (i in 1..3) {
//        delay(100) // 假装我们在这里做了一些有用的事情
//        emit(i) // 发送下一个值
//    }
//}
//
//fun main() = runBlocking<Unit> {
//    // 启动并发的协程以验证主线程并未阻塞
//    launch {
//        for (k in 1..3) {
//            println("I'm not blocked $k")
//            delay(100)
//        }
//    }
//    // 收集这个流
//    simple().collect { value -> println(value) }
//}