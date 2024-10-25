def filter_popular(reacts_2D, names, threshold):
    popular_users = []
    i = 0
    #iterate through reactions
    while i < len(reacts_2D):
        user_reacts = reacts_2D[i]
        username = names[i]
        total_engage = 0
        x = 0
        #iterate to get total engagement
        while x < len(user_reacts):
            total_engage = total_engage + user_reacts[x]
            x += 1
        if total_engage >= threshold:
            #add users into popular users list
            popular_users.append(username)
        i += 1
    return popular_users 


def gather_engagement(names, reacts, grouping):
    grouped = []
    reacts_index = 0
    #iterate through names
    for i in range(len(names)):
        username = names[i]
        grp_cnt = grouping[i]
        user_dta = [username]
        #iterate through grouping
        for x in range(grp_cnt):
            #adds reactions to username
            user_dta.append(reacts[reacts_index])
            reacts_index += 1
        #adds user data into grouped list
        grouped.append(user_dta)
    return grouped


def clear_zeros(reacts_2D):
    i = 0
    #iterate through list
    while i < len(reacts_2D):
        #list contains multiple lists
        sublist = reacts_2D[i]
        x = 0
        #iterate through sublist
        while x < len(sublist):
            if sublist[x] == 0:
                #removes 0
                sublist.pop(x)
            else:
                x += 1
        if sublist == []:
            #removes list entirely when empty
            del reacts_2D[i]
        else:
            i += 1
    return reacts_2D


def form_reactions_list(react_dict1, react_dict2):
    #generate list
    combined_list = []
    #put dicts together
    combined_dict = react_dict1.copy()
    #iterate through dictionaries
    for react, value in react_dict2.items():
        if react in combined_dict:
            #certain react in dictionaries adds values
            combined_dict[react] += value
        else:
            combined_dict[react] = value
    #convert to list
    for react, value in combined_dict.items():
        combined_list.append([react, value])
    return combined_list


def form_reactions_dict(reacts_2D):
    #dictionary
    reactions = {}
    total = 0
    for react, count in reacts_2D:
        #count is value that corresponds to react
        reactions[react] = count
        #adds up every value of key
        total += count
    reactions['total'] = total
    return reactions


