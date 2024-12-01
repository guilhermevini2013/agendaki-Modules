import {DayOfWeek} from "./dayOfWeek";

export interface DateJobReadDTO {
  id: number;
  dayOfWeek: DayOfWeek;
  scheduleInitial: string;
  scheduleFinal: string;
  breakInitial: string;
  breakFinal: string;
  isOpen: boolean;
}

export interface DateJobHolidayReadDTO {
  id: number;
  dateOfHoliday: string;
  scheduleInitial: string;
  scheduleFinal: string;
  breakInitial: string;
  breakFinal: string;
  isOpen: boolean;
}
