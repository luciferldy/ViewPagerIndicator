## ViewPagerIndicator

A simple indicator library for Android ViewPager like in Zhihutoday

![ screen_shot ](art/screen_shot.png)



[ ![Download](https://api.bintray.com/packages/luciferldy/maven/ViewPagerIndicator/images/download.svg) ](https://bintray.com/luciferldy/maven/ViewPagerIndicator/_latestVersion)

### Gradle

```groovy
dependencies {
	compile 'com.luciferldy.viewpagerindicator:viewpagerindicator:1.0.0'
}
```

### Usage

```xml
<com.luciferldy.viewpagerindicator.ViewPagerIndicator
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="50dip"
        android:layout_marginBottom="20dip"
        android:layout_gravity="bottom"
        app:indicator_size="8dip"
        app:indicator_distance="12dip"
        app:indicator_count="5"
        app:indicator_normal_color="@color/md_white"
        app:indicator_selected_color="@color/md_grey_700"/>
```

```java
final ViewPagerIndicator indicator = (ViewPagerIndicator) findViewById(R.id.indicator);
SimplePagerAdapter adapter = new SimplePagerAdapter(pager.getContext());
pager.setAdapter(adapter);
pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    indicator.moveIndicator(position, positionOffset, positionOffsetPixels);
  }

  @Override
  public void onPageSelected(int position) {
    indicator.setCurrentPosition(position);
  }

  @Override
  public void onPageScrollStateChanged(int state) {
  }
});
```

##### Properties

* `app:indicator_size`
* `app:indicator_distance`
* `app:indicator_count`
* `app:indicator_normal_color`
* `app:indicator_selected_color`

## Links

Thanks to [将你的代码上传 Bintray 仓库](http://www.cnblogs.com/cpacm/p/5548241.html) and [**CircleIndicator**: A lightweight viewpager indicator like in nexus 5 launcher](https://github.com/ongakuer/CircleIndicator).

## License

```
Copyright (C) 2017 luciferldy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

