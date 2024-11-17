export abstract class IMessageSender{
  public abstract sendValue(): { value:any, id:number, type:string };
}
