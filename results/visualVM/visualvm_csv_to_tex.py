import pandas as pd

# read in the two csv files with custom delimiter and thousands separator
df1 = pd.read_csv('load_test_quarkus.csv', delimiter=',', thousands=' ')
df2 = pd.read_csv('load_test_spring.csv', delimiter=',', thousands=' ')

# compute the required statistics for each dataframe
df1_stats = [df1['cpu'].str.replace(',', '.').astype(float).mean(),
             df1['cpu'].str.replace(',', '.').astype(float).max(),
             df1['size'].str.replace('\xa0', '').astype(int).max(),
             df1['used'].str.replace('\xa0', '').astype(int).mean(),
             df1['used'].str.replace('\xa0', '').astype(int).max(),
             df1['classes'].str.replace('\xa0', '').astype(int).max()]

df2_stats = [df2['cpu'].str.replace(',', '.').astype(float).mean(),
             df2['cpu'].str.replace(',', '.').astype(float).max(),
             df2['size'].str.replace('\xa0', '').astype(int).max(),
             df2['used'].str.replace('\xa0', '').astype(int).mean(),
             df2['used'].str.replace('\xa0', '').astype(int).max(),
             df2['classes'].str.replace('\xa0', '').astype(int).max()]

# create a new dataframe with the desired rows and columns
df_stats = pd.DataFrame({'File 1': df1_stats, 'File 2': df2_stats}, index=['cpu_avg', 'cpu_max', 'heap_allocated', 'heap_avg', 'heap_max', 'classes'])

# convert the dataframe to a TeX table and write it to a file
with open('output.tex', 'w') as f:
    f.write(df_stats.to_latex())
