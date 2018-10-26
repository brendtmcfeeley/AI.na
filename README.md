# FlowerAI

### Prerequisites
```
Python : https://www.python.org/downloads/

Android Studio : https://developer.android.com/studio/

TensorFlow : pip install --upgrade  "tensorflow==1.11.*"

PILLOW : pip install PILLOW
```

### Running the Application
Clone the repository
```
git clone https://github.com/brendtmcfeeley/FlowerAI.git
```
cd into the directory
```
cd FlowerAI
```

Test to make sure you Machine Learning Model is working
```
python -m scripts.label_image \
  --graph=tf_files/retrained_graph.pb  \
  --image=tf_files/flower_photos/daisy/3475870145_685a19116d.jpg
```

Example Output :
```
Evaluation time (1-image): 0.140s

daisy 0.7361
dandelion 0.242222
tulips 0.0185161
roses 0.0031544
sunflowers 8.00981e-06
```

### Open Android Studio
Follow these steps lol :
https://codelabs.developers.google.com/codelabs/tensorflow-for-poets-2-tflite/#3