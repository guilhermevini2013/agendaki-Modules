export interface ResponseScheduling {
  nameColum: string;
  value: string;
}

export interface Scheduling {
  responseScheduling: ResponseScheduling[];
  idScheduling: number;
}

export interface AllSchedulingReadDTO {
  columns: string[];
  allScheduling: Scheduling[];
}
