# 导入必要的库
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from keras.models import Sequential
from keras.layers import Embedding, Conv1D, GlobalMaxPooling1D, Dense
from keras.preprocessing.text import Tokenizer
from keras.preprocessing.sequence import pad_sequences
from keras.callbacks import EarlyStopping
import tensorflow as tf

# 读取评论数据，这里假设数据已经经过预处理，每行一个评论和一个情感极性标签
data = pd.read_csv('comments.csv')

# 分离输入和输出
X = data['comment'].values
y = data['sentiment'].values

# 将文本数据转换为数值序列，使用Tokenizer
tokenizer = Tokenizer(num_words=5000)
tokenizer.fit_on_texts(X)
X_seq = tokenizer.texts_to_sequences(X)

# 对数值序列进行填充，使得每个序列长度相同
max_len = max([len(x) for x in X_seq])
X_pad = pad_sequences(X_seq, maxlen=max_len, padding='post')

# 将数据集分成训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(X_pad, y, test_size=0.2, random_state=42)

# 构建卷积神经网络模型
model = Sequential()
model.add(Embedding(5000, 32, input_length=max_len))
model.add(Conv1D(64, 5, activation='relu'))
model.add(GlobalMaxPooling1D())
model.add(Dense(1, activation='sigmoid'))

# 编译模型，使用二元交叉熵作为损失函数
model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

# 定义早期停止回调函数
early_stop = EarlyStopping(monitor='val_loss', patience=2)

# 训练模型
model.fit(X_train, y_train, validation_data=(X_test, y_test), epochs=10, batch_size=32, callbacks=[early_stop])

# 在测试集上评估模型性能
score, acc = model.evaluate(X_test, y_test, batch_size=32)
print('Test score:', score)
print('Test accuracy:', acc)
