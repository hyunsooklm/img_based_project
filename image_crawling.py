import requests
from bs4 import BeautifulSoup
import urllib.request
import os
import shutil

open_api_key = '58OFrfvEmYaXf%2FgCXMXnnOixkQBr13mWFXDozqJTPHqWZ7jK4zsCQGcU64mdfPNmeWAs1JfijRXgob%2FsqlkNew%3D%3D'
open_url = 'http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?serviceKey=58OFrfvEmYaXf%2FgCXMXnnOixkQBr13mWFXDozqJTPHqWZ7jK4zsCQGcU64mdfPNmeWAs1JfijRXgob%2FsqlkNew%3D%3D&upkind=417000&state=protect&pageNo=1&numOfRows=10000'
# Open API URL 생성

res = requests.get(open_url)
soup = BeautifulSoup(res.content, 'html.parser')
# XML 도 Html 과 동일하게 html.parser 이용

image_dir= 'Abandon_dogs_image'

if os.path.exists(image_dir):
    shutil.rmtree(f'./{image_dir}')
if not os.path.exists(image_dir):
    os.makedirs(image_dir)
#디렉토리 삭제 후 재생성(폴더 비우기)

data = soup.find_all('item')
# XML 데이터에서 item 태그를 모두 불러와 data 변수에 저장
Image_Urllist=[]
print(f'보호중인 유기견 수:{len(data)}')
for item in data:
    image_url=item.find('popfile').get_text()
    Image_Urllist.append(image_url)
# '보호'태그 달린 공공데이터 상 유기견 이미지 url 긁어오기

for index,url in enumerate(Image_Urllist,start=1):
    try:
        urllib.request.urlretrieve(url, f'{image_dir}/test{index}.jpg')
        if index%100==0:
            print(f'현재 {index}장 저장완료')
    except Exception:
        print(f'{index} 사진은 에러뜸. pass')
print('이미지 저장이 완료되었습니다.')
#설정해놓은 디렉토리에 이미지 저장

