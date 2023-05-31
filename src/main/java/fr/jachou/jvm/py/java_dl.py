# -------------------------------------------- #
# JavaVersionDownloader Python by JavaVersionManager
# Download faster java version in python
# -------------------------------------------- #

# Class
class colors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'


# Imports
from time import sleep
import requests
import os
import urllib.request
import zipfile

# Def & Variables
loop = True


def downloader(version):
    print(colors.BOLD + "[~] Checking if the link is good...")
    response = requests.get(f"https://chiss.fr/jvm/download/Java_{version}.zip")
    if response.status_code == 200:
        print(colors.OKGREEN + "[✓] The link is good!")
        sleep(1)
        path = f"{os.path.expanduser('~')}/.jdks"
        print(colors.OKCYAN + f"The JDK {version} will be download in the folder '{path}'")
        if not os.path.exists(path):
            os.makedirs(path)
            print(colors.OKGREEN + "[✓] The folder has been created!")
        else:
            print(colors.WARNING + "[~] The folder already exists")
        sleep(1)
        print(colors.BOLD + "[~] Downloading...")
        urllib.request.urlretrieve(f"https://chiss.fr/jvm/download/Java_{version}.zip", f"{path}/Java_{version}.zip")
        print(colors.OKGREEN + f"[✓] The JDK {version} has been downloaded!")
        sleep(1)
        print(colors.BOLD + "[~] Unzipping...")
        with zipfile.ZipFile(f"{path}/Java_{version}.zip", 'r') as zip_ref:
            zip_ref.extractall(f"{path}/Java_{version}")
        print(colors.OKGREEN + f"[✓] The JDK {version} has been unzipped!")
        sleep(1)
        print(colors.BOLD + "[~] Deleting zip file...")
        os.remove(f"{path}/Java_{version}.zip")
        print(colors.OKGREEN + f"[✓] The JDK {version} has been deleted!")
        sleep(1)
        print(colors.OKGREEN + f"[✓] The JDK {version} has been downloaded and installed!")
        sleep(1)
    else:
        print(colors.FAIL + "[X] The link does not exists!")
        sleep(1)


# Message JVM = JavaVersionManager 

print(colors.HEADER + """
____________    ________  ___
______  /__ |  / /___   |/  /
___ _  / __ | / / __  /|_/ / 
/ /_/ /  __ |/ /  _  /  / /  
\____/   _____/   /_/  /_/   
                              
 """)

while loop:
    # Check if the value is an integer
    try:
        version = int(input(colors.OKCYAN + "Choose your version (only number) : "))
    except:
        # else return fail
        print(colors.FAIL + "You must enter an integer!")

        # Download
    downloader(version)
