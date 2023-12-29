import glob
import os

def combine_output_files(output_directory, combined_file_name):
    read_files = glob.glob(os.path.join(output_directory, 'part-*'))
    with open(combined_file_name, 'wb') as outfile:
        for f in read_files:
            with open(f, 'rb') as infile:
                outfile.write(infile.read())

def clean_output_directory(output_directory):
    files_to_remove = glob.glob(os.path.join(output_directory, '*'))
    for file in files_to_remove:
        os.remove(file)