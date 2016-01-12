#coding=utf-8
import numpy as np
import logging
import pprint as pp
import pandas as pd


def  load_csv_file(file_name):

    df = pd.read_csv('./dx_train.csv', header = 0)
    #print df
    # for tab delimited use:
    # df = pd.read_csv(input_file, header = 0, delimiter = "\t")
    print df.shape
    # put the original column names in a python list
    original_headers = list(df.columns.values)

    # remove the non-numeric columns
    df = df._get_numeric_data()


    # put the numeric column names in a python list
    numeric_headers = list(df.columns.values)

    # create a numpy array with the numeric values for input into scikit-learn
    numpy_array = df.as_matrix()

    return  numpy_array

def get_transection_from_data(origin_data):
    fo = open("foo.txt", "w")
    for row in origin_data:
        # last is label ,0 is black
        if(row[-1] == 0):
            index = np.where(row[0:-1] == 1)
            #print index[0].tostring() #index.tostring()
            VIstring = ' '.join(['%d' % num for num in index[0]])
            VIstring = '1 ' + VIstring
            print VIstring
            fo.writelines(VIstring + '\n')
    return


data = load_csv_file("")
get_transection_from_data(data)


#if __name__ == "__main__":
#    print ('Please run this script from train and test script')
