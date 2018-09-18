import requests
import unidecode
from bs4 import BeautifulSoup

locked_out = []
monsters_list = []


def crawler(max_pages):
    page = 1
    print "Scraping Monsters..."
    while page <= max_pages:
        print "Scraping page " + str(page) + "..."
        url = "https://www.dndbeyond.com/monsters?page=" + str(page)
        source_code = requests.get(url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, features="html.parser")
        for link in soup.findAll('a', {'class': 'link'}):
            url = link.get('href')
            if 'monsters' in url:
                href = 'https://www.dndbeyond.com' + str(url)
                if monster_analyzer(href) is False:
                    title = unidecode.unidecode(link.text)
                    locked_out.append(title)
        page += 1

    #print


def monster_analyzer(spell_url):
    #print
    lab = []
    val = []

    source_code = requests.get(spell_url)
    plain_text = source_code.text
    soup = BeautifulSoup(plain_text, features="html.parser")

    page = soup.find('h1', {'class': 'page-title'})
    if page is None:
        return False
    else:
        name = unidecode.unidecode(page.text).strip()
        monsters_list.append(name)

    '''page1 = soup.findAll('div', {'class': 'ddb-statblock-item-label'})
    for label in page1:
        lab.append(decoder(label))

    page2 = soup.findAll('div', {'class': 'ddb-statblock-item-value'})
    for value in page2:
        val.append(decoder(value))

    #page3 = soup.

    i = 0
    while i < len(lab):
        #print "\t" + decoder(lab[i]) + ": " + decoder(val[i])
        i += 1

    page4 = soup.find('div', {'class': 'more-info-content'})
    val.append(decoder(page4))
    #print "\tDescription: " + decoder(page4)
    spells_list.append(Spell(name, val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8]))'''
    return True


def decoder(scraped):
    if "Concentration" in unidecode.unidecode(scraped.text):
        return unidecode.unidecode(scraped.text).strip(' \t\n\r')
    else:
        return unidecode.unidecode(scraped.text).strip()


def list_printer(data=[]):
    for x in data:
        print x

crawler()

print
print "Monsters found: " + str(len(monsters_list))
for y in monsters_list:
    print y

print
print "LOCKED OUT: " + str(len(locked_out))
for x in locked_out:
    print x
