import nltk
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from sklearn.feature_extraction.text import TfidfVectorizer

# 读取数据
with open('data.txt', 'r', encoding='utf-8') as f:
    data = f.readlines()

# 分词
tokenizer = nltk.RegexpTokenizer(r'\w+')
data = [tokenizer.tokenize(comment.lower()) for comment in data]

# 去除停用词
stop_words = set(stopwords.words('english'))
data = [[word for word in comment if word not in stop_words] for comment in data]

# 词形还原
lemmatizer = WordNetLemmatizer()
data = [[lemmatizer.lemmatize(word, pos='v') for word in comment] for comment in data]

# 词向量化
tfidf = TfidfVectorizer()
data = tfidf.fit_transform([' '.join(comment) for comment in data])
