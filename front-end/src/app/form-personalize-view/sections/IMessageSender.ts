export abstract class IMessageSender{
  public abstract sendValue(): { value:string, id:number };
}
