import { DayOfWeek } from "./dayOfWeek";

export interface WeekCreateDTO {
  dayOfWeek: DayOfWeek;
  scheduleInitial: string;
  scheduleFinal: string;
  breakInitial: string;
  breakFinal: string;
}
