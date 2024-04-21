from datasets import load_dataset
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Embedding, LSTM, Dense, Dropout
from sklearn.model_selection import train_test_split
import numpy as np
import json
import shutil
from tensorflow.keras.callbacks import EarlyStopping
from google.colab import files

# Wczytanie danych
data = load_dataset("allegro/klej-polemo2-out", split='train')
texts = [x['sentence'] for x in data]
labels = [x['target'] for x in data]

# Przygotowanie danych tekstowych
tokenizer = Tokenizer(num_words=10000)
tokenizer.fit_on_texts(texts)
sequences = tokenizer.texts_to_sequences(texts)
data_padded = pad_sequences(sequences, maxlen=100)

# Zapisz stan tokenizatora do pliku JSON
tokenizer_json = tokenizer.to_json()
with open('tokenizer.json', 'w', encoding='utf-8') as f:
    f.write(json.dumps(tokenizer_json, ensure_ascii=False))

# Przetwarzanie etykiet
label_dict = {
    "__label__meta_zero": 0,
    "__label__meta_minus_m": 1,
    "__label__meta_plus_m": 2,
    "__label__meta_amb": 3
}
numeric_labels = np.array([label_dict[label] for label in labels])  # Konwersja na NumPy array

# Podział danych
train_data, test_data, train_labels, test_labels = train_test_split(data_padded, numeric_labels, test_size=0.2)

# Budowanie modelu
model = Sequential()
model.add(Embedding(10000, 128, input_length=100))
model.add(Dropout(0.5))
model.add(LSTM(64))
model.add(Dropout(0.5))
model.add(Dense(4, activation='softmax'))
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

early_stopping = EarlyStopping(monitor='val_loss', patience=3, restore_best_weights=True)

# Trenowanie modelu
model.fit(train_data, train_labels, batch_size=32, epochs=10, validation_data=(test_data, test_labels), callbacks=[early_stopping])

# Zapisanie modelu lokalnie w Colab
model_save_path = "./saved_model/my_model"
model.save(model_save_path, save_format="tf")

# Przenieś tokenizer.json do folderu assets modelu
shutil.move('tokenizer.json', model_save_path + '/assets/tokenizer.json')

# Spakowanie zapisanego modelu do formatu ZIP
!zip -r saved_model.zip ./saved_model

# Pobranie spakowanego modelu na dysk lokalny
files.download("saved_model.zip")
