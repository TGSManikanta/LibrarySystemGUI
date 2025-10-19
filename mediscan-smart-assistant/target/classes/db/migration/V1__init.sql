-- Users
CREATE TABLE IF NOT EXISTS users (
    user_id UUID PRIMARY KEY,
    full_name TEXT NOT NULL,
    email TEXT UNIQUE
);

-- Profiles
CREATE TABLE IF NOT EXISTS profiles (
    profile_id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    date_of_birth DATE,
    height_cm DOUBLE PRECISION,
    weight_kg DOUBLE PRECISION
);

-- Prescriptions
CREATE TABLE IF NOT EXISTS prescriptions (
    prescription_id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    source_file_path TEXT,
    ocr_text TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Prescription Items
CREATE TABLE IF NOT EXISTS prescription_items (
    item_id UUID PRIMARY KEY,
    prescription_id UUID NOT NULL REFERENCES prescriptions(prescription_id) ON DELETE CASCADE,
    drug_name TEXT,
    brand_name TEXT,
    strength TEXT,
    schedule TEXT,
    duration_days INTEGER,
    unit_price NUMERIC(12,2),
    start_date DATE
);

-- Reminders
CREATE TABLE IF NOT EXISTS reminders (
    reminder_id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    prescription_item_id UUID NOT NULL REFERENCES prescription_items(item_id) ON DELETE CASCADE,
    scheduled_time TIMESTAMPTZ NOT NULL,
    taken BOOLEAN NOT NULL DEFAULT FALSE
);

-- Adherence
CREATE TABLE IF NOT EXISTS adherence (
    adherence_id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    prescription_item_id UUID NOT NULL REFERENCES prescription_items(item_id) ON DELETE CASCADE,
    date DATE NOT NULL,
    taken BOOLEAN NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_prescriptions_user ON prescriptions(user_id);
CREATE INDEX IF NOT EXISTS idx_items_prescription ON prescription_items(prescription_id);
CREATE INDEX IF NOT EXISTS idx_reminders_user_time ON reminders(user_id, scheduled_time);
