export interface TemplateDTO {
  primaryColor: string;
  secondaryColor: string;
  sections: any[];
}

// export interface SectionToSaveDTO {
//   id: number;
//   typeSection: TypeSection;
//   position: number;
//   horizontalAlignment: string;
// }
//
// export interface InputDTO extends SectionToSaveDTO {
//   label: string;
//   width: number;
//   isIdentifier: boolean;
//   placeholder: string;
// }
//
// export interface HelpDTO extends SectionToSaveDTO {
//   title: string;
//   content: string[];
//   fontSizeTitle: string;
//   fontSizeContent: string;
// }
//
// export interface ImageDTO extends SectionToSaveDTO {
//   isPortfolio: boolean;
//   bio: string;
//   imageToBase64: string;
// }
//
// export interface CalendarDTO extends SectionToSaveDTO {
// }
//
// export interface ProfessionalAndService extends SectionToSaveDTO {
// }
//
// export enum TypeSection {
//   INPUT = 'input',
//   HELP = 'help',
//   IMAGE = 'image',
//   CALENDAR = 'calendar',
//   PROFESSIONAL = 'professionalAndService',
// }
