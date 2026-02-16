#!/bin/bash

# Use provided arguments or default values
source_folder="${1}"
target_folder="${2}"

# Check if source folder exists
if [ ! -d "$source_folder" ]; then
    echo "Source folder '$source_folder' does not exist."
    exit 1
fi

# Check if target folder exists
if [ ! -d "$target_folder" ]; then
    echo "Target folder '$target_folder' does not exist."
    exit 1
fi

# Log the start of the operation
echo "Replacing content of '$target_folder' with content from '$source_folder'..."

# Delete the entire content of the target folder
rm -rf "$target_folder"/*

# Copy the content from the source folder to the target folder (maintaining symlinks)
cp -rP "$source_folder"/* "$target_folder"

# Log the completion of the operation
echo "Content of '$target_folder' successfully replaced with content from '$source_folder'."
