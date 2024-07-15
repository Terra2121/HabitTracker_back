CREATE TABLE "users" (
    "user_id" BIGSERIAL,
    "name" varchar(100) NOT NULL,
    "email" varchar(200) NOT NULL,
    CONSTRAINT "unique_users" UNIQUE ("email"),
    CONSTRAINT "users_PK" PRIMARY KEY("user_id")
);

CREATE TABLE "habits" (
    "habit_id" BIGSERIAL,
    "name" varchar(100) NOT NULL,
    "user_id" integer NOT NULL,
    CONSTRAINT "habits_PK" PRIMARY KEY("habit_id"),
    CONSTRAINT "users_FK" FOREIGN KEY ("user_id") REFERENCES "users"("user_id") ON DELETE CASCADE
);

CREATE TABLE "executed_days"(
    "executed_day_id" BIGSERIAL,
    "habit_id" integer NOT NULL,
    "executed_day" DATE NOT NULL,
    CONSTRAINT "unique_executed_day" UNIQUE ("executed_day"),
    CONSTRAINT "days_PK" PRIMARY KEY("executed_day_id"),
    CONSTRAINT "habits_FK" FOREIGN KEY ("habit_id") REFERENCES "habits"("habit_id") ON DELETE CASCADE
);

