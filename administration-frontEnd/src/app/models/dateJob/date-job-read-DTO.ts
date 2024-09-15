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
