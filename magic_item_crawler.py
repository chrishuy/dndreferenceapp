import requests
import unidecode
from bs4 import BeautifulSoup


class Item:

    def __init__(self):
        self.name = ''
        self.details = ''
        self.description = []
        self.table_name = ''
        self.table_with_name_titles = []
        self.table_with_name_content = []
        self.table_no_name_titles = []
        self.table_no_name_content = []
        self.notes = ''

    def set_name(self, x):
        self.name = x

    def set_details(self, x):
        self.details = x

    def set_description(self, x):
        if x not in self.description:
            self.description.append(x)

    def set_table_name(self, x):
        self.table_name = x

    def set_table_with_name_titles(self, x):
        self.table_with_name_titles = x

    def set_table_with_name_content(self, x):
        self.table_with_name_content = x

    def set_table_no_name_titles(self, x):
        self.table_no_name_titles = x

    def set_table_no_name_content(self, x):
        self.table_no_name_content = x

    def set_notes(self, x):
        self.notes = x

    def print_object(self):
        print self.name
        print "Details: " + self.details
        print "Description: "
        for x in self.description:
            print x
        print self.table_name
        if len(self.table_with_name_titles) != 0:
            for x in self.table_with_name_titles:
                columnsize = len(x)
                index = self.table_with_name_titles.index(x)
                counter = 0
                for y in x:
                    print(str(y) + ", "),
                print
                for z in self.table_with_name_content[index]:
                    print(str(z) + ", "),
                    counter += 1
                    if counter >= columnsize:
                        counter = 0
                        print
                print

        if len(self.table_no_name_titles) != 0:
            for x in self.table_no_name_titles:
                columnsize = len(x)
                index = self.table_no_name_titles.index(x)
                counter = 0
                for y in x:
                    print(str(y) + ", "),
                print
                for z in self.table_no_name_content[index]:
                    print(str(z) + ", "),
                    counter += 1
                    if counter >= columnsize:
                        counter = 0
                        print
        print self.notes
        print

    def write_object_to_file(self):
        tables_exist = False
        returner = 'Name: ' + self.name
        returner += "\nDetails: " + self.details
        returner += "\nDescription: "
        for x in self.description:
            returner += '\n' + x

        if len(self.table_with_name_titles) != 0:
            tables_exist = True
            returner += '\n' + self.table_name
            returner += '\n'
            for x in self.table_with_name_titles:
                columnsize = len(x)
                index = self.table_with_name_titles.index(x)
                counter = 0
                for y in x:
                    returner += str(y) + "  "
                returner += '\n'
                for z in self.table_with_name_content[index]:
                    returner += str(z) + "  "
                    counter += 1
                    if counter >= columnsize:
                        counter = 0
                        returner += '\n'

        if len(self.table_no_name_titles) != 0:
            tables_exist = True
            for x in self.table_no_name_titles:
                returner += '\n'
                columnsize = len(x)
                index = self.table_no_name_titles.index(x)
                counter = 0
                for y in x:
                    returner += str(y) + "  "
                returner += '\n'
                for z in self.table_no_name_content[index]:
                    returner += str(z) + "  "
                    counter += 1
                    if counter >= columnsize:
                        counter = 0
                        returner += '\n'

        if tables_exist is False:
            returner += '\n'
        returner += self.notes
        return returner

locked_out = []
items_list = []


def crawler(max_pages):
    page = 1
    print "Scraping Magic Items..."
    while page <= max_pages:
        print "Scraping page " + str(page) + "..."
        url = "https://www.dndbeyond.com/magic-items?page=" + str(page)
        source_code = requests.get(url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, features="html.parser")
        for link in soup.findAll('a', {'class': 'link'}):
            url = link.get('href')
            if 'magic-items' in url:
                href = 'https://www.dndbeyond.com' + str(url)
                if item_analyzer(href) is False:
                    title = unidecode.unidecode(link.text)
                    locked_out.append(title.strip())
        page += 1


def item_analyzer(item_url):
    val = []
    table_title = []
    table_contents = []
    second_titles = []
    second_bodies = []
    temp_item_object = Item()

    source_code = requests.get(item_url)
    plain_text = source_code.text
    soup = BeautifulSoup(plain_text, features="html.parser")

    # Finds the name of the item. If there's a blockage, returns false and skips the item
    page = soup.find('h1', {'class': 'page-title'})
    if page is None:
        return False
    else:
        name = decoder(page)
        temp_item_object.set_name(name)
        # print name

    # Finds the Details of the item
    page_details = soup.findAll('div', {'class': 'details'})
    for label in page_details:
        val.append(decoder(label))
        temp_item_object.set_details(decoder(label))

    # Finds the description
    page_description = soup.findAll('div', {'class': 'more-info-content'})
    for desc in page_description:
        text = desc.findAll('p')
        for value in text:
            if '   ' not in value and len(value.findAll('table')) == 0 and decoder(value) != '' and len(value.findAll('strong')) == 0 and 'Notes:' not in decoder(value):
                if decoder(value) not in val:
                    temp_item_object.set_description(decoder(value))
                    # print "Description: " + decoder(value)
            if len(value.findAll('table')) != 0:
                temp_item_object.set_table_name(decoder(value.find('p')))
                page_table = value.findAll('table')
                if page_table is not None:
                    for nt in page_table:
                        # Find the table titles
                        page_table_heads = nt.findAll('thead')
                        if page_table_heads is not None:
                            for inc1 in page_table_heads:
                                temp = []
                                title = inc1.findAll('td')
                                if len(title) == 0:
                                    title = inc1.findAll('th')
                                for column in title:
                                    temp.append(decoder(column))
                            if len(temp) != 0:
                                table_title.append(temp)
                        # Find the table contents
                        page_table_contents = nt.findAll('tbody')
                        if page_table_contents is not None:
                            maxi = len(temp)
                            temper = []
                            for inc2 in page_table_contents:
                                content = inc2.findAll('tr')
                                if len(content) != 0:
                                    content = inc2.findAll('td')
                                column_count = 0
                                for column in content:
                                    temper.append(decoder(column))
                                    column_count += 1
                                    if column_count >= maxi:
                                        column_count = 0
                                table_contents.append(temper)


    # Find the tables if they're not encapsulated
    page_table = soup.findAll('table')
    if page_table is not None:
        for nt in page_table:
            # Find the table titles
            page_table_heads = nt.findAll('thead')
            if page_table_heads is not None:
                for inc1 in page_table_heads:
                    title = inc1.findAll('td')
                    if len(title) == 0:
                        title = inc1.findAll('th')
                    temp = []
                    for column in title:
                        temp.append(decoder(column))
                if len(temp) != 0:
                    #print temp
                    second_titles.append(temp)
            # Find the table contents
            page_table_contents = nt.findAll('tbody')
            if page_table_contents is not None:
                for inc2 in page_table_contents:

                    content = inc2.findAll('tr')
                    if len(content) != 0:
                        content = inc2.findAll('td')
                    maxi = len(temp)
                    temper = []
                    column_count = 0
                    for column in content:
                        temper.append(decoder(column))
                        column_count += 1
                        if column_count >= maxi:
                            column_count = 0
                second_bodies.append(temper)

    for x in table_contents:
        if x in second_bodies:
            index = second_bodies.index(x)
            second_bodies.remove(x)
            del second_titles[index]

    temp_item_object.set_table_with_name_titles(table_title)
    temp_item_object.set_table_with_name_content(table_contents)
    temp_item_object.set_table_no_name_titles(second_titles)
    temp_item_object.set_table_no_name_content(second_bodies)

    # Finds any notes that might be in the description
    page_notes = soup.find('p', {'class': 'notes-string'})
    if page_notes is not None:
        temp_item_object.set_notes(decoder(page_notes))

    items_list.append(temp_item_object)
    return True


def decoder(scraped):
    return unidecode.unidecode(scraped.text).strip()


def list_printer(data=[]):
    for x in data:
        print x

crawler(27)

with open('magic_items_list.txt', 'w') as f:
    for x in items_list:
        f.write(x.write_object_to_file())
        f.write('\n\n\n')

with open('blocked_magic_items.txt', 'w') as g:
    g.write("***********LOCKED MAGIC ITEMS: " + str(len(locked_out)) + "***********\n")
    for y in locked_out:
        g.write(y)
        g.write('\n')
