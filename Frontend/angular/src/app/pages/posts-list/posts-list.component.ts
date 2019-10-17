import { Component } from '@angular/core';
import { Post } from '@interfaces/post';

@Component({
    selector: 'app-posts-list',
    templateUrl: './posts-list.component.html',
    styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent {
    numbers = [1, 1, 1, 1, 1, 1, 1, 1, ];
    public POSTS: Post[] = [
        {
            id: 0,
            name: 'test1',
            image_path: 'https://picsum.photos/286/180',
            description: 'A developer position of 5E/hour.',
            localisation: 'Chisinau',
            start_date: '10/10/2019',
            domain_activity: 'IT'
        },
        {
            id: 1,
            name: 'New Ad',
            image_path: 'https://picsum.photos/286/180',
            description: 'A tester position of 5E/hour with a long long description of about 100 characters.',
            localisation: 'Chisinau',
            start_date: '10/10/2019',
            domain_activity: 'IT'
        },
        {
            id: 1,
            name: 'New Ad',
            image_path: 'https://picsum.photos/286/180',
            description: 'A tester position of 5E/hour with a long long description of about 100 characters.',
            localisation: 'Chisinau',
            start_date: '10/10/2019',
            domain_activity: 'IT'
        }
    ];
}
