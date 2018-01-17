这里需要用到词频统计。
考虑两种方式实现：一种是hash，一种是tire树。



trie树介绍：https://wizardforcel.gitbooks.io/the-art-of-programming-by-july/content/06.09.html
trie应用：
    1. 事先将已知的一些字符串（字典）的有关信息保存到trie树里，查找另外一些未知字符串是否出现过或者出现频率。
    2. 字符串最长公共前缀
    3. 字典序排序：Trie树是一棵多叉树，只要先序遍历整棵树，输出相应的字符串便是按字典序排序的结果。（对于英文来说）