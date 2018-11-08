# AI.na

![Aina Cover Photo](ainaCoverImage.png)

## Our Aina Vision
Our vision is to increase the percentage of threatened and endangered native species managed in Hawai'i by 2030 through a focus on sustainability goals demonstrated in the Aloha+ Challenge Dashboard. As students of the University of Hawai'i, we aim to ultimately provide sustainable solutions that are appropriately matched with technologies and platforms learned throughout our education career. We hope to give back to our Aina as much as it has given us.

## Prerequisites

### Linux
A basic understanding of Linux is highly recommended as we will be using the terminal / commandline. To learn more about linux and its commands click [here](https://maker.pro/linux/tutorial/basic-linux-commands-for-beginners).

### Python
Python is an interpreted, object-oriented, high-level programming language with dynamic semantics. It is utilized in conjunction with TensorflowLite to train a model to identify the plants. Click [here](https://www.python.org/downloads/) to install the most recent version of Python.

### TensorFlowLite & Pillow
After installing Python, the next thing you would want to install is TensorFlowLite using PIP that comes with the Python installation. TensorFlow Lite is the official solution for running machine learning models on mobile and embedded devices. It enables on‑device machine learning inference with low latency and a small binary size on Android, iOS, and other operating 
systems. To learn more click [here](https://www.tensorflow.org/lite/).

Pillow is the friendly PIL fork by [Alex Clark and Contributors](https://github.com/python-pillow/Pillow/graphs/contributors). PIL is the Python Imaging Library by Fredrik Lundh and Contributors.

In the terminal / commandline: 
```
pip install tensorflow
pip install PILLOW
```

### Android Studio
After installing Tensorflow, the next thing you need to install is Android Studio. Android Studio is the official [Integrated Development Environment (IDE)](https://searchsoftwarequality.techtarget.com/definition/integrated-development-environment) for [Android app](https://en.wikipedia.org/wiki/Android_(operating_system)) development, based on [IntelliJ IDEA](https://www.jetbrains.com/idea/). Android Studio will be used as our main working environment. To learn more and download, click [here](https://developer.android.com/studio/).

### Clone the Repository
Go to 

## Run the Application

## Much Mahalos

### HACC

### Sponsors

### Our Ohana
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
