# -*- coding: utf-8 -*-
"""
Created on Tue Aug 13 13:59:58 2019

@author: YEON
"""

import numpy as np
import pandas as pd

# 1.데이터 가져오기
data = pd.read_csv('SR_Data.csv')

# 2. Feature/label 나누기
X = data.iloc[:, :-1].values
y = data.iloc[:,-1].values

# 3. Clean Missing Data
from sklearn.impute import SimpleImputer
imputer = SimpleImputer(missing_values=np.nan, strategy = 'mean')
X[:, 1:] = imputer.fit_transform(X[:, 1:])

# 4. Make Categorical
from sklearn.preprocessing import OneHotEncoder, LabelEncoder
labelEncoder = LabelEncoder()
X[:, 0] = labelEncoder.fit_transform(X[:, 0])

onehotEncoder = OneHotEncoder(categorical_features=[0])
X = onehotEncoder.fit_transform(X).toarray()

# 5. Train/Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X,y,test_size = 0.2)

# 6. Standardation
from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train[:, 3:] = sc.fit_transform(X_train[:, 3:])
X_test[:, 3:] = sc.fit_transform(X_test[:, 3:])

# 7. Train
from sklearn.linear_model import LinearRegression
regressor = LinearRegression()
regressor.fit(X_train,y_train)

# 8. Predict
y_pred = regressor.predict(X_test)

from sklearn.linear_model import DecisionTressRegression
treeRegression = DecisionTressRegression()
treeRegression.fit(X_train,y_train)

y_pred_tree = treeRegression.predict(X_test)