import requests
import unidecode
from bs4 import BeautifulSoup
import io

locked_out = []
spells_list = []


class Spell:
    def __init__(self, name, level, casting, area, components, duration, school, attack, damageffect, description):
        self.name = name
        self.lvl = level
        self.cast = casting
        self.area = area
        self.components = components
        self.school = school
        self.attack = attack
        self.damage = damageffect
        self.description = description

        if len(area) > 10:
            first = str(area[0:6]).strip()
            second = str(area[6:])

            self.area = first + " " + second.strip()[:-1] + "cube)"
        else:
            self.area = area

        if "Concentration" in duration:
            temp = str(duration[13:])
            self.duration = "Concentration: " + temp.strip()
        else:
            self.duration = duration

    def get_name(self):
        return self.name

    def get_level(self):
        return self.lvl

    def get_casting(self):
        return self.cast

    def get_area(self):
        return self.area

    def get_components(self):
        return self.components

    def get_duration(self):
        return self.duration

    def get_school(self):
        return self.school

    def get_attack(self):
        return self.attack

    def get_damage(self):
        return self.damage

    def get_description(self):
        return self.description

    def print_spell(self):
        print "Name: " + self.name
        print "Level: " + self.lvl
        print "Casting Time: " + self.cast
        print "Range/Area: " + self.area
        print "Components: " + self.components
        print "Duration: " + self.duration
        print "School: " + self.school
        print "Attack/Save: " + self.attack
        print "Damage/Effect: " + self.damage

    def file_write(self):
        returner = "Name: " + self.name
        returner = returner + "\nLevel: " + self.lvl
        returner = returner + "\nCasting Time: " + self.cast
        returner = returner + "\nRange/Area: " + self.area
        returner = returner + "\nComponents: " + self.components
        returner = returner + "\nDuration: " + self.duration
        returner = returner + "\nSchool: " + self.school
        returner = returner + "\nAttack/Save: " + self.attack
        returner = returner + "\nDamage/Effect: " + self.damage
        returner = returner + "\nDescription: " + self.description
        return returner


def crawler(max_pages):
    page = 1
    print "Scraping Spells..."
    while page <= max_pages:
        print "Scraping page " + str(page) + "..."
        url = "https://www.dndbeyond.com/spells?page=" + str(page)
        source_code = requests.get(url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, features="html.parser")
        for link in soup.findAll('a', {'class': 'link'}):
            url = link.get('href')
            if 'spells' in url:
                href = 'https://www.dndbeyond.com' + str(url)
                if spell_analyzer(href) is False:
                    title = unidecode.unidecode(link.text)
                    locked_out.append(title)
        page += 1


def spell_analyzer(spell_url):
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

    page1 = soup.findAll('div', {'class': 'ddb-statblock-item-label'})
    for label in page1:
        lab.append(decoder(label))

    page2 = soup.findAll('div', {'class': 'ddb-statblock-item-value'})
    for value in page2:
        val.append(decoder(value))

    page3 = soup.find('div', {'class': 'more-info-content'})
    val.append(decoder(page3))
    spells_list.append(Spell(name, val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8]))
    return True


def decoder(scraped):
    if "Concentration" in unidecode.unidecode(scraped.text):
        return unidecode.unidecode(scraped.text).strip(' \t\n\r')
    else:
        return unidecode.unidecode(scraped.text).strip()


def list_printer(data=[]):
    for x in data:
        print x

crawler(24)
with open('spells_list.txt', 'w') as f:
    for x in spells_list:
        f.write(x.file_write())
        f.write('\n\n\n')

with open('blocked_spells.txt', 'w') as g:
    g.write("***********LOCKED SPELLS: " + str(len(locked_out)) + "***********\n")
    for y in locked_out:
        g.write(y)
        g.write('\n')

print "Finished scraping"