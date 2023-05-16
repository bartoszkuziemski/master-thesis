import pandas as pd

# read in the two csv files with custom delimiter and thousands separator
df1 = pd.read_csv('post_product/spring_post_product.csv', delimiter=',', thousands=' ')
df2 = pd.read_csv('post_product/quarkus_post_product.csv', delimiter=',', thousands=' ')

# compute the required statistics for each dataframe
df1_stats = [df1['cpu'].str.replace(',', '.').astype(float).mean(),
             df1['cpu'].str.replace(',', '.').astype(float).max(),
             df1['size'].str.replace('\xa0', '').astype(int).max() / 1000000,
             df1['used'].str.replace('\xa0', '').astype(int).mean() / 1000000,
             df1['used'].str.replace('\xa0', '').astype(int).max() / 1000000,
             df1['classes'].str.replace('\xa0', '').astype(int).max()]

df2_stats = [df2['cpu'].str.replace(',', '.').astype(float).mean(),
             df2['cpu'].str.replace(',', '.').astype(float).max(),
             df2['size'].str.replace('\xa0', '').astype(int).max() / 1000000,
             df2['used'].str.replace('\xa0', '').astype(int).mean() / 1000000,
             df2['used'].str.replace('\xa0', '').astype(int).max() / 1000000,
             df2['classes'].str.replace('\xa0', '').astype(int).max()]

# create a new dataframe with the desired rows and columns
df_stats = pd.DataFrame({'Spring': df1_stats, 'Quarkus': df2_stats}, index=['$cpu_{avg}$ [\%]', '$cpu_{max}$ [\%]', '$heap_{a}$ [MB]', '$heap_{avg}$ [MB]', '$heap_{max}$ [MB]', '$l_{c}$'])

# convert the dataframe to a TeX table and write it to a file
with open('output.tex', 'w') as f:
    f.write(df_stats.to_latex(float_format="%.2f").replace('\n', ' \\hline\n'))
