import requests
from bs4 import BeautifulSoup
import unidecode
import time as t

locked_out = []
equipment_list = []
equipment_counter = 0

def crawler(max_pages):
    page = 1
    print "Scraping Equipment..."
    while page <= max_pages:
        print "Scraping page " + str(page) + "..."
        url = "https://www.dndbeyond.com/equipment?page=" + str(page)
        source_code = requests.get(url, verify=False)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, features="html.parser")
        for link in soup.findAll('a', {'class': 'link'}):
            url = link.get('href')
            if 'equipment' in url:
                href = 'https://www.dndbeyond.com' + str(url)
                if equipment_analyzer(href) is False:
                    title = unidecode.unidecode(link.text)
                    locked_out.append(title)
        page += 1
        print '*' * 15


def equipment_analyzer(scraped):
    global equipment_counter
    equipment_counter += 1

    source_code = requests.get(scraped)
    plain_text = source_code.text
    soup = BeautifulSoup(plain_text, features="html.parser")


    page = soup.find('h1', {'class': 'page-title'})
    if page is None or "Found" in page:
        return False
    else:
        name = unidecode.unidecode(page.text).strip()
        print name

    page1 = soup.findAll('div', {'class': 'ddb-statblock-item-label'})
    for label in page1:
        lab.append(decoder(label))

    page2 = soup.findAll('div', {'class': 'ddb-statblock-item-value'})
    for value in page2:
        val.append(decoder(value))

    page3 = soup.find('div', {'class': 'more-info-content'})
    val.append(decoder(page3))
    return True

crawler(1)
print "Equipment scraped: " + str(equipment_counter)
print "Locked Out: " + str(len(locked_out))
for x in locked_out:
    print x
