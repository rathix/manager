import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Instance } from "./instance";
import { Observable } from "rxjs";

@Injectable()
export class InstanceService {
    instanceUrl: string = "http://freighter.rathix.com:8080/api/v1/instance";

    constructor(private http: HttpClient) {}

    createInstance(instance: Instance): Observable<Instance> {
        return this.http.post<Instance>(this.instanceUrl, instance);
    }

    retrieveInstances(): Observable<Instance[]> {
        return this.http.get<Instance[]>(this.instanceUrl);
    }

    retrieveInstance(id: string): Observable<Instance> {
        return this.http.get<Instance>(this.instanceUrl + "/" + id);
    }

    updateInstance(id: string, instance: Instance): Observable<Instance> {
        return this.http.put<Instance>(this.instanceUrl + "/" + id, instance);
    }

    deleteInstance(id: string): Observable<Instance> {
        return this.http.delete<Instance>(this.instanceUrl + "/" + id);
    }
}