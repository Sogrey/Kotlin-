# 内联函数inline noinline 与 crossinline

特殊情况：

* kotlin中，内部Lambda是不允许中断外部函数执行
* inline的Lambda可以中断外部函数调用
* crossinline不允许inline的Lambda中断外部函数执行
* noinline拒绝内联

