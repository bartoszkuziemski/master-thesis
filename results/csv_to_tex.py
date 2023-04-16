import csv

# Open the CSV file and read the data
with open('data.csv', newline='') as csvfile:
    data_reader = csv.DictReader(csvfile)
    data = [row for row in data_reader]

# Calculate the required metrics from the data
req_num = int(data[0]['req_num'])
req_per_sec = req_num / (float(data[0]['duration']) / 1000000)
errors = sum([int(data[0]['err_connect']), int(data[0]['err_read']), int(data[0]['err_write']), int(data[0]['err_status']), int(data[0]['err_timeout'])])
errors_percentage = errors / req_num * 100
average_latency = float(data[0]['latency_avg'])
p50 = float(data[0]['p50'])
p75 = float(data[0]['p75'])
p90 = float(data[0]['p90'])
p99 = float(data[0]['p99'])
p99_9 = float(data[0]['p99_9'])
p99_99 = float(data[0]['p99_99'])
p99_999 = float(data[0]['p99_999'])
p100 = float(data[0]['p100'])

# Generate the LaTeX table
table = '\\begin{table}\n'
table += '\t\\begin{tabular}{|l|c|}\n'
table += '\t\t\\hline\n'
table += '\t\tMetric & Value \\\\ \\hline\n'
table += '\t\treq/s & {:.2f} \\\\ \\hline\n'.format(req_per_sec)
table += '\t\terror & {} \\\\ \\hline\n'.format(errors_percentage)
table += '\t\taverage latency & {:.2f} \\\\ \\hline\n'.format(average_latency)
table += '\t\tP50 & {:.2f} \\\\ \\hline\n'.format(p50)
table += '\t\tP75 & {:.2f} \\\\ \\hline\n'.format(p75)
table += '\t\tP90 & {:.2f} \\\\ \\hline\n'.format(p90)
table += '\t\tP99 & {:.2f} \\\\ \\hline\n'.format(p99)
table += '\t\tP99.9 & {:.2f} \\\\ \\hline\n'.format(p99_9)
table += '\t\tP99.99 & {:.2f} \\\\ \\hline\n'.format(p99_99)
table += '\t\tP99.999 & {:.2f} \\\\ \\hline\n'.format(p99_999)
table += '\t\tP100 & {:.2f} \\\\ \\hline\n'.format(p100)
table += '\t\\end{tabular}\n'
table += '\\end{table}'

# Print the LaTeX table
with open('output.tex', 'w') as outfile:
    outfile.write(table)
