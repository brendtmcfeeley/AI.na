from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
import requests
import time
import sys

#driver = webdriver.Chrome()

def main():
    #driver.get('https://www.google.com')
    print(sys.argv[1]) # Arg 1 will be used for the item to be searched for

if __name__ == '__main__':
    main()