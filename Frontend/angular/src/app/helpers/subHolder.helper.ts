import {Subscription} from 'rxjs';

export class SubHolderHelper {

    public subs: Subscription[] = [];
    // tslint:disable-next-line:ban-types
    public listeners: Function[] = [];

    set subscribe(sub: Subscription) {
        this.subs.push(sub);
    }

    // tslint:disable-next-line:ban-types
    set listen(listener: Function) {
        this.listeners.push(listener);
    }

    public clear() {
        this.clearSubs();
        this.clearListeners();
    }

    public clearSubs() {
        this.subs.forEach((sub: Subscription) => sub.unsubscribe());
        this.subs = [];
    }

    public clearListeners() {
        // tslint:disable-next-line:ban-types
        this.listeners.forEach((listener: Function) => listener());
        this.listeners = [];
    }

}
