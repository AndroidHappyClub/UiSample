# 2021/9/11 扶摇直上九万里

- 引入**DataBinding**
- 修改了下列Activity和Fragment布局文件里面的warning
- ViewPagerActivity改动
  - onClick里面的判断由switch修改为**if**判断,原因如下：
    > Resource IDs will be non-final by default in Android Gradle Plugin version 8.01,avoid using them in switch case statements
- PagerListAdapter
  - 完善了方法注解
- ViewPagerOneActivity(Two Three Four Always)
  - inflate里面的root参数指定为对应的根布局  
- ImageListViewActivity
  - 解决了以下问题
    >ImageListViewActivity.java使用了未经检查或不安全的操作。 