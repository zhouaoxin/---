import pandas as pd
from snownlp import sentiment


def train_model():
    print("开始训练模型...")
    data = pd.read_csv(r"./DataSet.csv", header=0)
    train = data.iloc[:40000, [1, 2]]
    test = data.iloc[40000:, [1, 2]]
    train_neg = train.iloc[:, 1][train.label == 0]
    train_pos = train.iloc[:, 1][train.label == 1]
    train_neg.to_csv(r"./neg.csv", index=0, header=0)
    train_pos.to_csv(r"./pos.csv", index=0, header=0)
    test.to_csv(r"./TestModel.csv", index=0, columns=['label', 'review'])
    sentiment.train(r'./neg.csv', r'./pos.csv')
    print("模型训练完成！！！")
    sentiment.save(r'E:\毕业设计\python情感分析\venv\Lib\site-packages\snownlp\sentiment\sentiment.marshal.3')


if __name__ == '__main__':
    train_model()
