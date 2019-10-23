# Probe
Web crawling app for android
특정 사이트 크롤링을 위한 앱입니다.

## 컴퓨터 구성 / 필수 조건 안내 (Prerequisites)
Android studio
Java Development Kit

## 설치 안내 (Installation Process)
[Android Studio installation](https://developer.android.com/studio/install)을 참고해주세요.

## 사용법 (Getting Started)

## 파일 정보 및 목록 (File Manifest)
probe/fragment/
Fragment 관련 파일입니다. Single Activity 구조를 따르고 있습니다.
[Navigation Component](https://developer.android.com/guide/navigation)을 이용하여 각각의 Fragment로 이동합니다.

probe/site
특정 사이트 관련 파일입니다. 해당 사이트 게시판에 대한 크롤링 기능이 하드코딩 되어 있습니다.

probe/data
RecyclerView관련 데이터 클래스와 adapter 파일입니다.

## 저작권 및 사용권 정보 (Copyright / End User License)
GNU General Public License v3.0 
자세한 내용은 LICENSE.md를 참고해주세요.

## 배포자 및 개발자의 연락처 정보 (Contact Information)
Hyun Deok Kim - @upbo on GitHub
khd1326@naver.com

## 알려진 버그 (Known Issues)
Data관련 파일들을 json파일에 기록할때 append하지 않고 처음부터 끝까지 새로 기록합니다.

## FAQ

## Help Wanted
크롤링할때마다 Favicon을 불러들이지 않고 미리 다운로드 받아놓은 후 사용
json파일 read/write 최적화

## 