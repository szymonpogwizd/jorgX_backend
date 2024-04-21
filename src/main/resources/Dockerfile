FROM tensorflow/tensorflow:2.15.0

WORKDIR /app

# Skopiuj aplikację predykcji i tokenizer do katalogu roboczego w kontenerze
COPY prediction/ /app/

# Skopiuj zapisany model do katalogu roboczego w kontenerze
COPY model/ /app/model/

# Zainstaluj zależności Pythona
RUN pip install --ignore-installed flask

# Informuj Docker, że kontener nasłuchuje na określonym porcie
EXPOSE 5000

# Uruchom aplikację Flask
CMD ["python", "prediction.py"]
