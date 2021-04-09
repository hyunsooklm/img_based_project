import requests
import xmltodict
import json
from pprint import pprint
import pymysql

url = 'http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic'
params = {
    'serviceKey' : 'Rus9dDKGLW82fx3Pa1TAdg03ZE6Z0NjE1bHto1KZyzscvcHAFnwl3K4ImVdgj50R3b6Vgkce/v4oEuNg0y0QAg==',
    'upkind' : '417000',
    'state' : 'protect',
    'pageNo' : '1',
    'numOfRows':'10000'
}
res = requests.get(url, params=params)
#print(res.text)

# xml -> dict
dict_data = xmltodict.parse(res.text)

# dict -> json
json_data = json.dumps(dict_data)

# json -> dict
dict_data = json.loads(json_data)
'''
pprint(dict_data['response']['body']['items']['item'])
print(len(dict_data['response']['body']['items']['item']))
'''
conn = None
cur = None
sql="" 
# 메인 코드 
conn = pymysql.connect(host='34.64.190.82', user='root', password='abandog', db='abandog_data', charset='utf8')
# 접속정보
cur = conn.cursor(pymysql.cursors.DictCursor)
# 커서생성
sql = 'TRUNCATE `dog`'
cur.execute(sql)
sql = "INSERT INTO `dog` VALUES (%(age)s, %(careAddr)s, %(careNm)s, %(careTel)s, %(colorCd)s, %(popfile)s, %(kindCd)s, %(sexCd)s, %(neuterYn)s, cid) ON DUPLICATE KEY UPDATE cid = cid+1;"
cur.executemany(sql, dict_data['response']['body']['items']['item'])
conn.commit()
conn.close()