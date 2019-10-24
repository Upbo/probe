# Probe
특정 사이트 크롤링을 위한 앱입니다.  
기존에 있는 포털 사이트에서 검색을 할 경우 포털 사이트의 목적은 검색어를 포함한 정보를 최대한 많이 보여주는 것 입니다.  
따라서 옵션을 통해 기간이나 사이트를 지정해줘도 불필요한 정보가 과다하게 나옵니다.  
이로인해 유저에게 필요한 정보가 묻히고, 유저 입장에서는 다른 사이트에서 다시 검색해보거나 직접 사이트를 들어가서 검색하는 게 나은 경우가 많습니다.  
게다가 검색을 원하는 사이트가 여러곳이라면 검색에 드는 시간은 점점 늘어나게 됩니다.  
이처럼 특정 정보를 원할 때 소비하는 시간을 줄이고자 만들게 되었습니다.  

향후에는 백그라운드에서 검색을 해주어서 새로운 글이 올라올 경우 알람이 뜨게 할 예정입니다.


## 컴퓨터 구성 / 필수 조건 안내 (Prerequisites)
Android Studio      
Java Development Kit  

## 설치 안내 (Installation Process)
[Android Studio Installation](https://developer.android.com/studio/install)을 참고해주세요.

## 사용법 (Getting Started)
앱을 실행하고 오른쪽 상단의 +버튼을 눌러 데이터를 추가하신 후 사용하시면 됩니다.

## 파일 정보 및 목록 (File Manifest)
* fragment/  
Fragment 관련 파일입니다. Single Activity 구조를 따르고 있습니다.  
[Navigation Component](https://developer.android.com/guide/navigation)을 이용하여 각각의 Fragment로 이동합니다.  

* site/  
특정 사이트 관련 파일입니다. 해당 사이트 게시판에 대한 크롤링 기능이 하드코딩 되어 있습니다.  

* data/  
RecyclerView관련 데이터 클래스와 adapter 파일입니다.  

## 저작권 및 사용권 정보 (Copyright / End User License)
GNU General Public License v3.0  
자세한 내용은 [LICENSE](https://github.com/Upbo/probe/blob/master/LICENSE)를 참고해주세요.  

## 배포자 및 개발자의 연락처 정보 (Contact Information)
Hyun Deok Kim - @upbo on GitHub  
khd1326@naver.com  

## 알려진 버그 (Known Issues)
Data관련 파일들을 json파일에 기록할때 append하지 않고 처음부터 끝까지 새로 기록합니다.  
새로운 사이트를 코드 상으로 추가했을때 기존에 있는 json파일을 삭제해야 json파일에 정상적으로 추가가 됩니다.  

## 문제 발생에 대한 해결책 (Troubleshooting)
Issue를 작성해주시거나 문제 수정 후 Pull Request를 올려주세요.  

## 크레딧 (Credit)
### 개발자
Hyun Deok Kim  

### 라이브러리  
* #### jsoup  
Java HTML Parser  
https://jsoup.org  

* #### glide
An image loading and caching library for Android focused on smooth scrolling  
https://github.com/bumptech/glide  

## 업데이트 정보 (Change Log)
#### development build alpha01
Okky, 퀘이사존, 루리웹의 일부 게시판이 추가되었습니다.  


