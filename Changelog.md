# 2021/9/11 扶摇直上九万里

## 修改记录如下：

- 引入**DataBinding**
- 修改了下列Activity和Fragment布局文件里面的warning
- ViewPagerActivity
  - onClick里面的判断由switch修改为**if**判断,原因如下：
    **Resource IDs will be non-final by default in Android Gradle Plugin version 8.01,avoid using them in switch case statements**
- PagerListAdapter
  - 完善了方法注解
- ViewPagerOneActivity(Two Three Four Always)
  - inflate里面的root参数指定为对应的根布局
    修改前：
    ```java
      li.inflate(R.layout.page_ad_one,null,false)
    ```
    修改后：
    ```java
      li.inflate(R.layout.page_ad_one,linearLayout,false)
    ```
- ImageListViewActivity
  - 解决了ImageListViewActivity.java使用了未经检查或不安全的操作。
    修改前：
    ```java
    DataAdapter<Book> _adapter = new DataAdapter<Book>((ArrayList) _books, R.layout.list_view_item)
    ``` 
    修改后：
    ```java
    DataAdapter<Book> _adapter = new DataAdapter<Book>((ArrayList<Book>) _books, R.layout.list_view_item)
    ``` 
- BottomViewPagerActivity 
  - 页面从ViewPager迁移到ViewPager2 
- DialogActivity
  - onClick里面的判断由switch修改为**if**判断。

## 待解决问题   
- DialogActivity
  - 滚动等待对话框里面的线程问题：
    **Call to 'Thread.sleep()' in a loop, probably busy-waiting**  