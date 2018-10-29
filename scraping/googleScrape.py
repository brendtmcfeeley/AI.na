from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
import requests
import time
import sys

driver = webdriver.Chrome()
findElement = driver.find_element_by_xpath

def startSearch(searchParam):
    if (not searchParam):
        return None
        
    findElement("//input[@aria-label='Search']").send_keys('yes')

def main():
    driver.get('https://www.google.com')
    startSearch(sys.argv[1])

if __name__ == '__main__':
    main()