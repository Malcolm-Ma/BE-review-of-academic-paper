# Use an official Python runtime as a parent image
FROM python:3.10-slim

# Set the working directory in the container
WORKDIR /app

# Add maintainer info
LABEL maintainer="malcolm.m.ma@outlook.com"

# Install any needed packages specified in requirements.txt
COPY requirements.txt /app/requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Make port 5001 available to the world outside this container
EXPOSE 5001

# Copy the current directory contents into the container at /app
COPY . /app

# Define environment variable
ENV NAME World

# Run app.py when the container launches
CMD ["python", "app.py"]
