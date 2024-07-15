-- V2__Update_executed_days_table.sql
-- Drop the existing unique constraint
ALTER TABLE "executed_days" DROP CONSTRAINT "unique_executed_day";

-- Add the new unique constraint within the scope of habit_id
ALTER TABLE "executed_days" ADD CONSTRAINT "unique_executed_day" UNIQUE ("habit_id", "executed_day");
