from pprint import pprint
import jieba.analyse


# 数据清洗
def data_clean(list_data):
    # 读取文件
    print("数据清洗中...")
    with open('E:\毕业设计\python情感分析\情感分析\data.txt', 'r', encoding='utf-8') as file:
        for item in file:
            # 去除空格
            item = item.replace(' ', '')
            list_data.append(item)
    # 查看数据长度
    print('共有数据', len(list_data))
    print("数据清洗完成")


# 获取前十关键词
def topWords10():
    # top10高频词
    str_content = ' '.join(str(i) for i in list_data)
    keywords_top10 = jieba.analyse.extract_tags(str_content, withWeight=True, topK=10)
    print('top10关键词及权重')
    # pprint(keywords_top10)
    result = {k: v for k, v in keywords_top10}
    return result


if __name__ == '__main__':
    list_data = []
    data_clean(list_data)
    result = topWords10()
    print(result)
