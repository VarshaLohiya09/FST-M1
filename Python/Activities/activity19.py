# Import pandas
import pandas
from pandas import ExcelWriter

# Create a dictionary that will be used to create the DataFrame
data = {
    'FirstName':["Satvik", "Avinash", "Lahri"],
    'LastName':["Shah", "Kati", "Rath"],
    'Email':["satshah@example.com", "avinashK@example.com", "lahri.rath@example.com"],
    'PhoneNumber':["4537829158", "4892184058", "4528727830"]
}

# Create the DataFrame that will be written to the Excel file
dataframe = pandas.DataFrame(data)

# Print the dataframe
print(dataframe)

# Write the dataframe to an Excel file
writer = ExcelWriter('sample.xlsx')
dataframe.to_excel(writer, sheet_name='Sheet1', index=False)

writer._save()