DrawerPage
==========

DrawerPage is an Open Source Android library that allows developers to easily create Activity 
whose Content View is like a drawer which can be slid right or left to show the Activity below it or to 
close itself. If you slide it to the right NO more than half of its width, you can see the the Activity 
below it, and it will bounce back after you release it. If you  slide it to the right more than half of 
its width, it will slide out and close the Activity after you release it.

Setup
-----
* In Eclipse, just import the library as an Android library project. Project > Clean to generate the 
binaries you need, like R.java, etc.
* Then, just add DrawerPage as a dependency to your existing project.
* Make your Activity extends from DrawerPageActivity and use the Theme.DrawerPage.Translucent.NoTitleBar or its derivation as Activity theme.

Note: Any Activity that extends DrawerPageActivity must use the Theme.DrawerPage.Translucent.NoTitleBar or its derivation as its theme.
License
-------

    Copyright 2014-2016 Leo Guo
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
