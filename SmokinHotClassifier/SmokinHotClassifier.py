
import tensorflow as tf

# Training and Testing Data Sets
print("\n==========")
print("Getting the Training and Validations Datasets")
batch_size = 10
img_height = 1000
img_width = 1000


val_ds  = tf.keras.utils.image_dataset_from_directory("Data/TestingJPG/",image_size=(img_height, img_width),batch_size=batch_size)
train_ds  = tf.keras.utils.image_dataset_from_directory("Data/TrainingJPG/",image_size=(img_height, img_width),batch_size=batch_size)

# Autotune Performance
AUTOTUNE = tf.data.AUTOTUNE
train_ds = train_ds.cache().prefetch(buffer_size=AUTOTUNE)
val_ds = val_ds.cache().prefetch(buffer_size=AUTOTUNE)

# Build the Model using the training data
print("\n==========")
print("Building the Model")
num_classes = 5
model = tf.keras.Sequential([
    tf.keras.layers.Rescaling(1./255),
    tf.keras.layers.Conv2D(32, 3, activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Conv2D(32, 3, activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Conv2D(32, 3, activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(num_classes)
])

model.compile(
    optimizer='adam',
    loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
    metrics=['accuracy'])

# Compare the model to the validation data
print("\n==========")
print("Compare the model to the validation data")
model.fit(
    train_ds,
    validation_data=val_ds,
    epochs=3
)