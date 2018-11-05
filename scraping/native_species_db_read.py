import pyrebase

config = {
    "apiKey": "AIzaSyA5iBsdCVUJXflcdcmzAzL-gEtiefTShtI",
    "authDomain": "flowerai-59022.firebaseapp.com",
    "databaseURL": "https://flowerai-59022.firebaseio.com",
    "projectId": "flowerai-59022",
    "storageBucket": "flowerai-59022.appspot.com",
    "messagingSenderId": "815989022056"
  }

def main():
  firebase = pyrebase.initialize_app(config)
  database = firebase.database()

  plants = database.child("native_plants/").get()
  for user in plants.each():
    print(user.val())

if __name__ == '__main__':
    main()