export class Spark{
    id: string = "";
    content: string = "";
    creator: {
        username: string,
        name: string,
    } = {username: "", name: ""};
    lightCount: number = 0;
    createdAt: Date = new Date();
    lastEditedAt?: Date = new Date();
    deleted?: boolean = false;

    constructor(){
    }

    static fromObject(object: any): Spark {
        const n = new Spark();
        n.id = object.id;
        n.content = object.content;
        n.creator.username = object.creator.username;
        n.creator.name = object.creator.name;
        n.lightCount = object.lightCount;
        n.createdAt = new Date(object.createdAt);
        n.lastEditedAt = new Date(object.lastEditedAt);
        n.deleted = object.deleted;
        return n;
    }
}